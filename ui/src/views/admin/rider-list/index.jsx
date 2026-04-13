import React, { useEffect, useState } from "react";
import DataTable from "components/table/DataTable";
import MasterDataLayout from "components/layout/MasterDataLayout";
import { productService } from "services/productService";
import { MdAdd, MdCardGiftcard, MdAssignment } from "react-icons/md";

export default function RiderList() {
  const [data, setData] = useState([]);
  const [types, setTypes] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    Promise.all([
      productService.getAllRiders(),
      productService.getAllRiderTypes()
    ])
      .then(([resRiders, resTypes]) => {
        setData(resRiders.data || []);
        setTypes(resTypes.data || []);
      })
      .catch(err => console.error(err))
      .finally(() => setLoading(false));
  }, []);

  const columns = [
    { header: "ID", accessorKey: "id" },
    { header: "Code", accessorKey: "code" },
    { header: "Name", accessorKey: "name" },
    {
      header: "Rider Type",
      accessorKey: "riderTypeId",
      cell: info => {
        const typeId = info.getValue();
        const type = types.find(t => t.id === typeId);
        return <span className="font-bold text-emerald-600 dark:text-emerald-400">{type ? type.name : "N/A"}</span>;
      }
    },
    { header: "Active", accessorKey: "isActive", cell: info => info.getValue() ? <span className="text-green-500 font-bold">Yes</span> : <span className="text-red-500 font-bold">No</span> },
  ];

  const workspaceActions = [
    {
      label: "Add Rider",
      desc: "Define new benefit",
      icon: <MdAdd className="h-6 w-6" />,
      color: "text-emerald-600 bg-emerald-50 dark:bg-emerald-900/30",
      isPrimary: true,
      onClick: () => alert("Opening Add Rider...")
    },
    {
      label: "Coverage Matrix",
      desc: "Relationship mapping",
      icon: <MdAssignment className="h-5 w-5" />,
      color: "text-purple-600 bg-purple-50 dark:bg-purple-900/30",
      onClick: () => alert("Opening Matrix...")
    },
    {
      label: "Gift Incentives",
      desc: "Bonus logic config",
      icon: <MdCardGiftcard className="h-5 w-5" />,
      color: "text-pink-600 bg-pink-50 dark:bg-pink-900/30",
      onClick: () => alert("Incentives...")
    }
  ];

  return (
    <MasterDataLayout title="Workspace" actions={workspaceActions}>
      {loading ? (
        <div className="flex h-64 items-center justify-center">
          <span className="text-gray-400 font-bold animate-pulse uppercase tracking-widest text-xs">Syncing Riders...</span>
        </div>
      ) : (
        <DataTable columns={columns} data={data} filename="Riders_Export" />
      )}
    </MasterDataLayout>
  );
}
