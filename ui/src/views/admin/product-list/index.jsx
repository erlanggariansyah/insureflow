import React, { useEffect, useState } from "react";
import DataTable from "components/table/DataTable";
import MasterDataLayout from "components/layout/MasterDataLayout";
import { productService } from "services/productService";
import { MdAdd, MdCloudDownload, MdSettings } from "react-icons/md";

export default function ProductList() {
  const [data, setData] = useState([]);
  const [types, setTypes] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    Promise.all([
      productService.getAllProducts(),
      productService.getProductTypes()
    ])
      .then(([resProducts, resTypes]) => {
        setData(resProducts.data || []);
        setTypes(resTypes.data || []);
      })
      .catch(err => console.error("Error fetching products:", err))
      .finally(() => setLoading(false));
  }, []);

  const columns = [
    { header: "ID", accessorKey: "id" },
    { header: "Code", accessorKey: "code" },
    { header: "Name", accessorKey: "name" },
    {
      header: "Product Type",
      accessorKey: "productTypeId",
      cell: info => {
        const typeId = info.getValue();
        const type = types.find(t => t.id === typeId);
        return <span className="font-bold text-blue-600 dark:text-blue-400">{type ? type.name : "N/A"}</span>;
      }
    },
    { header: "Active", accessorKey: "isActive", cell: info => info.getValue() ? <span className="text-green-500 font-bold">Yes</span> : <span className="text-red-500 font-bold">No</span> },
  ];

  const workspaceActions = [
    {
      label: "Create Product",
      desc: "Manual entry for new product",
      icon: <MdAdd className="h-6 w-6" />,
      color: "text-blue-600 bg-blue-50 dark:bg-blue-900/30",
      isPrimary: true,
      onClick: () => alert("Opening Create Product Modal...")
    },
    {
      label: "Import Catalog",
      desc: "Bulk upload via CSV/JSON",
      icon: <MdCloudDownload className="h-5 w-5" />,
      color: "text-navy-600 bg-gray-100 dark:bg-navy-900/30",
      borderColor: "border-l-navy-600",
      onClick: () => alert("Opening Import...")
    },
    {
      label: "Attributes",
      desc: "Metadata management",
      icon: <MdSettings className="h-5 w-5" />,
      borderColor: "border-l-gray-400",
      onClick: () => alert("Opening Attributes...")
    }
  ];

  return (
    <MasterDataLayout title="Workspace" actions={workspaceActions}>
      {loading ? (
        <div className="flex h-64 items-center justify-center">
          <span className="text-gray-400 font-bold animate-pulse uppercase tracking-widest text-xs">Synchronizing Products...</span>
        </div>
      ) : (
        <DataTable columns={columns} data={data} filename="Products_Export" />
      )}
    </MasterDataLayout>
  );
}
