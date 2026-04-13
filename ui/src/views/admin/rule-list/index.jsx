import React, { useEffect, useState } from "react";
import DataTable from "components/table/DataTable";
import MasterDataLayout from "components/layout/MasterDataLayout";
import { productService } from "services/productService";
import { MdAdd, MdTerminal, MdGavel } from "react-icons/md";

export default function RuleList() {
  const [data, setData] = useState([]);
  const [types, setTypes] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    Promise.all([
      productService.getAllRules(),
      productService.getAllRuleTypes()
    ])
      .then(([resRules, resTypes]) => {
        setData(resRules.data || []);
        setTypes(resTypes.data || []);
      })
      .catch(err => console.error(err))
      .finally(() => setLoading(false));
  }, []);

  const columns = [
    { header: "ID", accessorKey: "id" },
    { header: "Name", accessorKey: "name" },
    {
      header: "Type",
      accessorKey: "type",
      cell: info => {
        const typeObj = info.getValue();
        const typeName = typeObj?.name || "N/A";
        return <span className="rounded-md bg-navy-50 px-2 py-1 text-[10px] font-extrabold uppercase tracking-wider text-navy-700 dark:bg-navy-900 dark:text-white">
          {typeName}
        </span>;
      }
    },
    {
      header: "Expression",
      accessorKey: "expression",
      cell: info => <code className="rounded bg-gray-100 px-2 py-1 text-xs text-red-500 dark:bg-navy-900">{info.getValue()}</code>
    },
    { header: "Message", accessorKey: "message" },
  ];

  const workspaceActions = [
    {
      label: "Add Validation Rule",
      desc: "New logical constraint",
      icon: <MdAdd className="h-6 w-6" />,
      color: "text-orange-600 bg-orange-50 dark:bg-orange-900/30",
      isPrimary: true,
      onClick: () => alert("Opening Rule Form...")
    },
    {
      label: "Expression Tester",
      desc: "Debug SpEL syntax",
      icon: <MdTerminal className="h-6 w-6" />,
      color: "text-blue-600 bg-blue-50 dark:bg-blue-900/30",
      borderColor: "border-l-blue-600",
      onClick: () => alert("Opening Tester...")
    },
    {
      label: "Rule Hierarchy",
      desc: "Execution order",
      icon: <MdGavel className="h-5 w-5" />,
      borderColor: "border-l-gray-400",
      onClick: () => alert("Opening Order...")
    }
  ];

  return (
    <MasterDataLayout title="Workspace" actions={workspaceActions}>
      {loading ? (
        <div className="flex h-64 items-center justify-center">
          <span className="text-gray-400 font-bold animate-pulse uppercase tracking-widest text-xs">Synchronizing Rules...</span>
        </div>
      ) : (
        <DataTable columns={columns} data={data} filename="Rules_Export" />
      )}
    </MasterDataLayout>
  );
}
